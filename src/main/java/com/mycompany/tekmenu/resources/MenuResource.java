/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tekmenu.resources;

import com.mycompany.tekmenu.Enteties.Menu;
import com.mycompany.tekmenu.beans.MenuBean;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("menu")
@Produces(MediaType.APPLICATION_JSON)
public class MenuResource {

    @EJB
    MenuBean menuBean; 
    @GET
    public Response getMenu(){
        Menu menu = menuBean.getMenu();
        return Response.ok(menu).build();
    }
    
}
