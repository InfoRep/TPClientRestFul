package service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import client.controle.MultiController;

public class ClientService {
	private String baseUrl;
		
	public ClientService(String baseUrl) {
		super();
		this.baseUrl = baseUrl;
	}

	public Object get(String path) throws JSONException, NotValidException
	{
		Client c = Client.create();
		WebResource webResource = c.resource(baseUrl);
		String rep = webResource
								.path(path)
								.accept(MediaType.APPLICATION_JSON)
								.get(String.class);
		
		JSONObject obj = new JSONObject(rep);
		if (!(Boolean)obj.get("valid"))
			throw new NotValidException();
		
		return obj.get("msg");
	}
	
	public String postForm(String path, MultivaluedMap formData) throws JSONException, NotValidException
	{
		Client c = Client.create();
		WebResource webResource = c.resource(baseUrl);

		String rep = webResource
						.path(path)
						.type("application/x-www-form-urlencoded")
						.post(String.class, formData);
		
		JSONObject obj = new JSONObject(rep);
		System.out.println(obj.get("msg"));
		if (!(Boolean)obj.get("valid"))
			throw new NotValidException();
		
		if (obj.get("msg") != null)
			return obj.get("msg").toString();
		return null;
	}
}
