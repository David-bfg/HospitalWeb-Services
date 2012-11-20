package com.mayo.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.mayo.db.AutoCompleteRest;

@Path("/AutoComplete")
public class AutoCompleteWS {

	AutoCompleteRest autoCompleteRest = AutoCompleteRest.getInstance();

	@Path( "{category}" )
    @GET
	public String get(@PathParam("category") String category) {
		System.out.println("Category:"+category);
	return	autoCompleteRest.getList(category);
	}
}