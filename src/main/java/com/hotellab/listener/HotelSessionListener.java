/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotellab.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author eennis
 */
public class HotelSessionListener implements HttpSessionListener {
    
    public static int sessionCount = 0;
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ++sessionCount;   
        se.getSession().getServletContext().setAttribute("sessionCount", HotelSessionListener.sessionCount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        --sessionCount;     
        se.getSession().getServletContext().setAttribute("sessionCount", HotelSessionListener.sessionCount);
    }
    
}
