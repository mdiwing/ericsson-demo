package com.redhat;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class PetResource {

    private List<Pet> pets = new ArrayList<Pet>();

    @GET
    @Path("/pets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pets() {
        return Response.ok(new GenericEntity<List<Pet>>(pets){}).build();
    }

    @GET
    @Path("/pets/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPetById(@PathParam("id") int id) {
        Pet petWithId = null;

        for (Pet pet : pets) {
            if (pet.getId() == id) {
                petWithId = pet;
                break;
            }
        }

        if (petWithId != null) {
            return Response.ok().entity(petWithId).build();
        }

        return Response.status(404).entity("Pet with id "+id+" not found").build();
    }

    @POST
    @Path("/pets")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createPet(Pet pet) {

        boolean found = false;

        for (int i=0; i < pets.size(); i++) {
            if (pets.get(i).getId() == pet.getId()) {
                found = true;
                break;
            }
        }
 
        if (!found) {
          pets.add(pet);
        }
        
        return Response.ok().build();
    }

    @DELETE
    @Path("/pets/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response deletePet(@PathParam("id") int id) {
        
        for (int i=0; i < pets.size(); i++) {
            if (pets.get(i).getId() == id) {
                pets.remove(i);
                break;
            }
        }

        return Response.ok().build();
    }

}
