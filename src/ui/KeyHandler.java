package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp)
    {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e)
    {
        // Title State
        if(gp.gameState == gp.titleState)
        {
            if(gp.ui.titleScreenState == 0)
            {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_W -> {
                        gp.ui.screenState[0].commandNum--;
                        if (gp.ui.screenState[0].commandNum < 0)
                            gp.ui.screenState[0].commandNum = 2;
                    }
                    case KeyEvent.VK_S -> {
                        gp.ui.screenState[0].commandNum++;
                        if (gp.ui.screenState[0].commandNum > 2)
                            gp.ui.screenState[0].commandNum = 0;
                    }
                    case KeyEvent.VK_ENTER -> {
                        switch (gp.ui.screenState[0].commandNum)
                        {
                            case 0 -> gp.ui.titleScreenState = 1;
                            case 1 -> {}
                            case 2 -> System.exit(0);
                        }
                    }
                }
            }
            else if(gp.ui.titleScreenState == 1)
            {
            	switch (e.getKeyCode())
                {
                    case KeyEvent.VK_W -> {
                        gp.ui.screenState[1].commandNum--;
                        if (gp.ui.screenState[1].commandNum < 0)
                            gp.ui.screenState[1].commandNum = 5;
                    }
                    case KeyEvent.VK_S -> {
                        gp.ui.screenState[1].commandNum++;
                        if (gp.ui.screenState[1].commandNum > 5)
                            gp.ui.screenState[1].commandNum = 0;
                    }
                    case KeyEvent.VK_ENTER -> {
                        boolean notBack = false;
                        int index = 0;
                        switch (gp.ui.screenState[1].commandNum)
                        {
                            case 0 -> index = 0;
                            case 1 -> index = 1;
                            case 2 -> index = 2;
                            case 3 -> index = 3;
                            case 4 -> index = 4;
                            case 5 -> {
                                gp.ui.titleScreenState = 0;
                                gp.ui.screenState[0].commandNum = 0;
                                notBack = true;
                            }
                        }

                        if(!notBack)
                        {
                        	if(gp.snake.unlock[index])
                        	{
                            	gp.mapPick = index;
                                gp.gameState = gp.playState;
                                gp.snake.setXAndY(index);
                        	}
                        	else
                        		gp.ui.screenState[1].warningOn = true;
                        }
                    }
                }
            }
        }
        else if(gp.gameState == gp.pauseState)
        {
            if (e.getKeyCode() == KeyEvent.VK_P)
                gp.gameState = gp.playState;
            else if (e.getKeyCode() == KeyEvent.VK_ENTER)
            {
            	gp.gameState = gp.titleState;
            	gp.snake.totApple = 0;
            	gp.ui.messageOn = false;
            }
        }
        else if(gp.gameState == gp.playState && !gp.ui.gameFinished)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_W -> gp.snake.direction = "up";
                case KeyEvent.VK_S -> gp.snake.direction = "down";
                case KeyEvent.VK_A -> gp.snake.direction = "left";
                case KeyEvent.VK_D -> gp.snake.direction = "right";
                case KeyEvent.VK_P -> gp.gameState = gp.pauseState;
                case KeyEvent.VK_ENTER -> enterPressed = true;
            }
        }
        else if(gp.gameState == gp.playState && gp.ui.gameFinished)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_ENTER -> {
                	gp.gameState = gp.titleState;
                	gp.ui.gameFinished = false;
                	gp.ui.messageOn = false;
                }
            }
        }
        else if(gp.gameState == gp.gameOverState)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_ENTER -> {
                	gp.gameState = gp.titleState;
                	gp.snake.totApple = 0;
                	gp.ui.messageOn = false;
                }
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W -> upPressed = false;
            case KeyEvent.VK_S -> downPressed = false;
            case KeyEvent.VK_A -> leftPressed = false;
            case KeyEvent.VK_D -> rightPressed = false;
        }
    }
}
