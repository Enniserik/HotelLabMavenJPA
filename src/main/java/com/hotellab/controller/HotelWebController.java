/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotellab.controller;

import com.hotellab.ejb.HotelFacade;
import com.hotellab.entity.Hotel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eennis
 */
public class HotelWebController extends HttpServlet {

    private static final String RESULT_PAGE = "index.jsp";
    private static final long serialVersionUID = 1L;
    
    @EJB
    private HotelFacade hotelService;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
 
        if(request.getParameter("arraySpace") != null){
            session.setAttribute("hotel", request.getParameter("arraySpace"));
        }
        
        List<Hotel> hotelInfo = hotelService.findAll();  
        
        String typeString = request.getParameter("type");
        
        if(typeString != null && typeString.equals("view")){
            
            buildHotelInfo(Integer.parseInt(request.getParameter("arraySpace")), hotelInfo, request); 
            hotelInfo = hotelService.findAll();
            
        } else if(typeString != null && typeString.equals("delete")){
            
            Hotel h = hotelInfo.get(Integer.parseInt(request.getParameter("arraySpace")));
            hotelService.remove(h);
            hotelInfo = hotelService.findAll();
            
        } else if(typeString != null && typeString.equals("create")){
            
            List<String> hotelParams = getHotelInfo(request);
            
            Hotel h = new Hotel();
            h.setHotelName(hotelParams.get(0));
            h.setStreetAddress(hotelParams.get(1));
            h.setCity(hotelParams.get(2));
            h.setState(hotelParams.get(3));
            h.setPostalCode(hotelParams.get(4));
            h.setNotes(hotelParams.get(5));
            
            hotelService.create(h);
            //name, address, city, state, zip, notes
            
            hotelInfo = hotelService.findAll();
            
        } else if(typeString != null && typeString.equals("update")){
            
            int id = Integer.parseInt(request.getParameter("hotel_id"));
            List<String> hotelParams = getHotelInfo(request);
            
            for(Hotel h : hotelInfo){
                if(h.getHotelId() == id){
                    h.setHotelName(hotelParams.get(0));
                    h.setStreetAddress(hotelParams.get(1));
                    h.setCity(hotelParams.get(2));
                    h.setState(hotelParams.get(3));
                    h.setPostalCode(hotelParams.get(4));
                    h.setNotes(hotelParams.get(5));
                    hotelService.edit(h);
                }
            }
            
            hotelInfo = hotelService.findAll();
            buildHotelInfo(Integer.parseInt(request.getParameter("arraySpace")), hotelInfo, request);  
            
        } else {
            
            if(session.getAttribute("hotel") != null){     
                int slot = Integer.parseInt(session.getAttribute("hotel").toString());
                buildHotelInfo(slot, hotelInfo, request);  
            }
            
            String option = request.getParameter("dropdownOption");
            //System.out.println(option);
        
            if(option.equals("all")){
                hotelInfo = hotelService.findAll();
//            } else if (option.equals("byCity")){
//                hotelInfo = hs.retrieveHotelsByColumnVal("city", request.getParameter("searchVal"));
//            } else {
//                hotelInfo = hs.retrieveHotelsByColumnVal("state", request.getParameter("searchVal"));
            }
        }

        request.setAttribute("hotels", hotelInfo);
        
        RequestDispatcher view =
                request.getRequestDispatcher(response.encodeURL(RESULT_PAGE));
        view.forward(request, response);
        
    }
    
    private void buildHotelInfo(int arrayIdx, List<Hotel> hotelInfo, HttpServletRequest request){
        
        Hotel h = hotelInfo.get(arrayIdx);
        request.setAttribute("hotelName", h.getHotelName());
        request.setAttribute("address", h.getStreetAddress());
        request.setAttribute("city", h.getCity());
        request.setAttribute("state", h.getState());
        request.setAttribute("postal", h.getPostalCode());
        request.setAttribute("notes", h.getNotes());
        
    }
    
    private List<String> getHotelInfo(HttpServletRequest request){
        
        List<String> hotelParams = new ArrayList<>();
        
        hotelParams.add(request.getParameter("name"));
        hotelParams.add(request.getParameter("address"));
        hotelParams.add(request.getParameter("city"));
        hotelParams.add(request.getParameter("state"));
        hotelParams.add(request.getParameter("postal"));
        hotelParams.add(request.getParameter("notes"));
            
        return hotelParams;
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
