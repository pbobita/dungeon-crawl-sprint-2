package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.dao.ItemDao;
import com.codecool.dungeoncrawl.dao.JdbcDao;
import com.codecool.dungeoncrawl.dao.PlayerDao;
import com.codecool.dungeoncrawl.data.ItemFactory;
import com.codecool.dungeoncrawl.logic.actors.ItemService;
import com.codecool.dungeoncrawl.logic.actors.MovementService;
import com.codecool.dungeoncrawl.ui.UI;
import com.codecool.dungeoncrawl.ui.elements.StatusPane;
import com.codecool.dungeoncrawl.ui.keyeventhandler.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Set;

public class Game extends Application {
    private UI ui;
    private GameLogic logic;
    private Set<KeyHandler> keyHandlers;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        JdbcDao jdbcdao = new JdbcDao();
        ItemFactory itemFactory = new ItemFactory();
        PlayerDao playerDAO = new PlayerDao(jdbcdao, itemFactory);
        MovementService movementService = new MovementService();
        ItemService itemService = new ItemService();
        ItemDao itemDAO = new ItemDao(jdbcdao);
        ItemDao.init(itemDAO);
        GameLogic logic = new GameLogic(playerDAO, movementService, itemService, itemDAO);
        this.logic = logic;
        this.ui = new UI(logic);

        StatusPane statusPane = ui.getMainStage().getStatusPane();
        this.keyHandlers = Set.of(new Up(statusPane), new Down(statusPane), new Left(statusPane), new Right(statusPane), new ESC());
        ui.setKeyHandlers(keyHandlers);

        ui.setUpPain(primaryStage);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }
}
