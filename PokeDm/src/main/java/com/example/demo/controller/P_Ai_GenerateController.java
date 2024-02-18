package com.example.demo.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class P_Ai_GenerateController {

	@RequestMapping(path = "/poke_Ai_generate/{Poke_prompt}", method = RequestMethod.GET)
	
	public String battle_gra(@PathVariable String Poke_prompt, Model model) throws IOException, InterruptedException {
	//	Poke_prompt="fire";
		System.out.print(Poke_prompt);
		
		
		
			HttpRequest request = HttpRequest.newBuilder()
				    .uri(URI.create("https://api.prodia.com/v1/sdxl/generate"))
				    .header("accept", "application/json")
				    .header("content-type", "application/json")
				    .header("X-Prodia-Key", "3d1ca348-aed2-4fa3-86dd-7583f2f5ef4a")
				    .method("POST", HttpRequest.BodyPublishers.ofString("{\"model\":\"sd_xl_base_1.0.safetensors [be9edd61]\",\"prompt\":\""+Poke_prompt+"\",\"negative_prompt\":\"badly drawn\",\"style_preset\":\"anime\",\"steps\":20,\"cfg_scale\":7,\"seed\":-1,\"sampler\":\"DPM++ 2M Karras\",\"width\":512,\"height\":512}"))
				    .build();
				HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
				System.out.println(response.body());
		
		String jobResponse = response.body();
        String jobID = extractJobID(jobResponse);

        JSONObject myObject = new JSONObject(response.body());
		
		System.out.println(myObject);
		
		
		
		
		HttpRequest requestJob = HttpRequest.newBuilder()
			    .uri(URI.create("https://api.prodia.com/v1/job/"+jobID))
			    .header("accept", "application/json")
			    .header("X-Prodia-Key", "3d1ca348-aed2-4fa3-86dd-7583f2f5ef4a")
			    .method("GET", HttpRequest.BodyPublishers.noBody())
			    .build();
			HttpResponse<String> responseJob = HttpClient.newHttpClient().send(requestJob, HttpResponse.BodyHandlers.ofString());
			System.out.println(responseJob.body());
			String IdResponse =responseJob.body();
			
			
			while ("generating".equals(extractStatus(IdResponse))) {
	             System.out.println("Job is still generating. Waiting...");
	             Thread.sleep(5000); // Wait for 5 seconds before checking again

	             // Re-send the request to get updated status
	             responseJob = HttpClient.newHttpClient().send(requestJob, HttpResponse.BodyHandlers.ofString());
	             IdResponse = responseJob.body();
	         }
			
			System.out.println(responseJob.body());
			String ImgId=extractImgUrl(IdResponse);
			System.out.println(ImgId);
			
		return responseJob.body();

	}
	private static String extractJobID(String response) {
	     return response.split("\"job\":\"")[1].split("\"")[0];
	 }
	private static String extractImgUrl(String response) {
	     return response.split("\"imageUrl\":\"")[1].split("\"")[0];
	 }
	
	private static String extractStatus(String response) {
	     return response.split("\"status\":\"")[1].split("\"")[0];
	 }
	}