package com.bitcoin.service;

import com.bitcoin.repository.CoinWillBeListenedRepository;
import com.bitcoin.repository.NewOrderLocaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by İlker ÇATAK on 1/26/18.
 */
@Service
public class BinanceConnectionManager {

    @Autowired
    ApplicationContext applicationContext;

    public static  boolean alive=false;

    BinanceConnectionService binanceConnectionService;
    static {

        System.setProperty("java.awt.headless", "false");
    }

    //This is necessary for re initializing intellij idea, api call is not reliable.
    @Scheduled(fixedDelay = 3000000,initialDelay = 3000000)
    public void refreshConnection(){

        try {
            Robot robot = new Robot();
            robot.setAutoDelay(1);
            System.out.println("girdi key robot");
            robot.delay(1000);

            // Simulate a key press
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.delay(100);
            robot.keyPress(KeyEvent.VK_F10);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_F10);
            robot.delay(2000);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (AWTException e) {
            e.printStackTrace();
        }
//        if(!alive) {
//            createNewConnection();
//            System.out.println("not alive , creating new one");
//        }{
//            System.out.println("connected");
//        }
//        alive=false;
    }
//    @Scheduled(fixedDelay = 1200000 , initialDelay = 1200000 )
//    public void refresh(){
//        if(binanceConnectionService!=null) {
//            binanceConnectionService.refresh();
//        }
//        System.out.println(" sözde refreshed");
//    }

    @PostConstruct
    public void init(){
        createNewConnection();
    }

    private void createNewConnection() {
        System.out.println("Created new conection");
        BinanceConnectionService binanceConnectionService=null;
        binanceConnectionService=new BinanceConnectionService((NewOrderLocaleRepository)applicationContext.getBean("newOrderLocaleRepository"),
            (NewOrderLocaleService) applicationContext.getBean("newOrderLocaleServiceImpl"),(CoinWillBeListenedRepository) applicationContext.getBean("coinWillBeListenedRepository"));
        binanceConnectionService.initializeConnection();
    }

    public static void connectionAlive(){
        alive=true;
//        System.out.println("alive");
    }
}
