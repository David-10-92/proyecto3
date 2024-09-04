package com.game_service_api.game_service.service.impl;

import com.game_service_api.game_service.common.dtos.CreateGame;
import com.game_service_api.game_service.common.dtos.UpdateGame;
import com.game_service_api.game_service.common.entities.GameModel;
import com.game_service_api.game_service.repository.GameRepository;
import com.game_service_api.game_service.service.GameService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public GameModel createGame(String userId,CreateGame createGame) {
        System.out.println(userId);
        return Optional.of(createGame)
                .map(game -> mapToEntity(userId,createGame))
                .map(gameRepository::save)
                .orElseThrow(()-> new RuntimeException("Error creating the game"));
    }

    @Override
    public GameModel getGame(String userId,Long gameId) {
        return Optional.of(gameId)
                .flatMap(gameRepository::findById)
                .orElseThrow(() -> new RuntimeException("Game not found"));
    }

    @Override
    public void updateGame(String userId,UpdateGame updateGame, Long gameId) {
        Optional.of(gameId)
                .map(this::getGameByGameId)
                .map(existGame -> updateFieldsGame(existGame,updateGame))
                .map(gameRepository::save);
    }

    @Override
    public void deleteGame(String userId,Long gameId) {
        Optional.of(gameId)
                .map(this::getGameByGameId)
                .ifPresent(gameRepository::delete);
    }

    private GameModel updateFieldsGame(GameModel existGame, UpdateGame updateGame) {
        existGame.setNameGame(updateGame.getNameGame());
        return existGame;
    }

    private GameModel getGameByGameId(Long gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));
    }

    private GameModel mapToEntity(String userId,CreateGame createGame){
        return GameModel.builder()
                .nameGame(createGame.getNameGame())
                .userId(Integer.parseInt(userId))
                .build();
    }
}
