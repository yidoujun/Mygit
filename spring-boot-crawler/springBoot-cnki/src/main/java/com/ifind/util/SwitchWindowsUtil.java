package com.ifind.util;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;

import java.util.Iterator;
import java.util.Set;

/**
 * 根据句柄/页面标题切换窗口
 *
 * @author 易某某
 * @date 2020/3/20 10:27
 */
public class SwitchWindowsUtil {

    /**
     *  根据句柄切换窗口
     * @param dr
     * @param currentHandle 当前窗口句柄
     * @param handles       所有窗口句柄
     */
    public static void switchWindowsByHandle(WebDriver dr, String currentHandle, Set<String> handles) {
        Iterator<String> it = handles.iterator();
        while (it.hasNext()) {
            String newHandles = it.next();
            if(currentHandle.equals(newHandles)) {
                continue;
            }
            try {
                String newHandle = it.next();
                // 切换到新窗口
                WebDriver window = dr.switchTo().window(newHandle);
                System.out.println("New page Title is:" + window.getTitle());
                System.out.println("New page URL is:" + window.getCurrentUrl());
            }catch (Exception e) {
                System.out.println("无法切换至新打开的窗口");
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 根据页面标题切换窗口
     *
     * @param driver
     * @param windowTitle 页面标题
     * @return
     */
    protected boolean switchToWindowByTitle(WebDriver driver,String windowTitle){
        boolean flag = false;
        try {
            String currentHandle = driver.getWindowHandle();
            Set<String> handles = driver.getWindowHandles();
            for (String s : handles) {
                if (s.equals(currentHandle))
                    continue;
                else {
                    driver.switchTo().window(s);
                    if (driver.getTitle().contains(windowTitle)) {
                        flag = true;
                        System.out.println("Switch to window: "
                                + windowTitle + " successfully!");
                        break;
                    } else
                        continue;
                }
            }
        } catch (NoSuchWindowException e) {
            System.out.printf("Window: " + windowTitle  + " cound not found!", e.fillInStackTrace());
            flag = false;
        }
        return flag;
    }

}
