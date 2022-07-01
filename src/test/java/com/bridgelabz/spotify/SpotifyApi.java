package com.bridgelabz.spotify;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SpotifyApi {
	String token;
	String user_id = "hzil1bztsv1tkm68ekvrbxlza";
	String playlist_id ="4nwAqvVbIZyz8j4u2e5A1g";
	String track_id;

	@BeforeTest
	public void getToken() {
		token = "Bearer BQC9Pb7KnuTenpy0lymK7oTQarBNognLWyGA6w9d3o4ld5pDVBk-PUt9Jx6whu7NcV_bKuwKvxfgJ0GGtYcxlg8ukaZSnaOr-ZPGbO4i2NZwcOUIkVOf13lr1F7Z4-WFiKfapJ2kCx24cyZcV6eQWCHueXuW-AA37N3TwTW-W_LL-Bd1pGesFsll07AJs85RtS1rEHA2r9KKj3DMA4YBh7-UBB97xKGOnADptvQWJwtiCuY5AvRyk2c5tiUopxjkXmNlh9M1fLh2j58y2-ihQWHd_fpggTQP48Kg-YHsj89ABTU68MqJPG5V2QisqZJCt7gjdg";
      }
	
	@BeforeTest
	public void getTrack() {
	track_id = "5O932cZmzOZGOGZz9RHx20";
	}
	
	//Get Current Users Profile	
	@Test (priority = 1)
	public void testGet_CurrentUsersProfile() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("	https://api.spotify.com/v1/me");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}

	//Get User Profile
	@Test (priority = 2)
	public void testGet_Users_Profile() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/"+user_id+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	// Post Create Playlist
	@Test (priority = 3)
	public void createPlaylist() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{\r\n"
						+ "  \"name\": \"Automation Api\",\r\n"
						+ "  \"description\": \"New playlist description\",\r\n"
						+ "  \"public\": false\r\n"
						+ "}")
				.when()
				.post("https://api.spotify.com/v1/users/"+user_id+"/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(201);

	}
	
	
	//Post Add Items to Playlist
		@Test (priority = 4)
		public void addItemstoPlaylist() {
			Response response = RestAssured.given().contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.header("Authorization", token)
					.queryParams("uris","spotify:track:46NYX9zIml71qtfYYjakTI,spotify:track:5X5mUYJ9TUwosuEAZFzKEX,spotify:track:5WzfGg2ueNoOS5aIkaR9qX,spotify:track:5O932cZmzOZGOGZz9RHx20" )
					.when()
					.post("https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");
			response.prettyPrint();
			response.then().assertThat().statusCode(201);
		}
		
		//Put Update Playlist Items
		@Test (priority = 5)
		public void updatePlaylistItems() {
			Response response = RestAssured.given().contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.header("Authorization", token)
					.body("{\r\n"
							+ "  \"range_start\": 1,\r\n"
							+ "  \"insert_before\": 0,\r\n"
							+ "  \"range_length\": 3\r\n"
							+ "}")
					.when()
					.put("https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");
			response.prettyPrint();
			response.then().assertThat().statusCode(200);
		}
		
		
		// Put Change Playlist Details
		@Test (priority = 6)
		public void changePlaylistDetails() {
			Response response = RestAssured.given().contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.header("Authorization", token)
					.body("{\r\n"
							+ "  \"name\": \"Automation api\",\r\n"
							+ "  \"description\": \"Updated playlist description\",\r\n"
							+ "  \"public\": false\r\n"
							+ "}")				
					.when()
					.put("https://api.spotify.com/v1/playlists/"+playlist_id+"");
			response.prettyPrint();
			response.then().assertThat().statusCode(200);
		}
		
//	    Put Add Custom Playlist Cover Image	
//		@Test (priority = 7)
//		public void addCustomPlaylistCoverImage() {
//			Response response = RestAssured.given().contentType(ContentType.JSON)
//					.accept(ContentType.JSON)
//					.header("Authorization", token)
//					.when()
//					.put("https://api.spotify.com/v1/playlists/"+playlist_id+"/images");
//			response.prettyPrint();
//			response.then().assertThat().statusCode(200);
//		}
		
	
	//Get Current User Playlist
	@Test (priority = 8)
	public void getCurrentUserPlaylists() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/me/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	//Get User Playlists
	@Test (priority = 9)
	public void getUserPlaylists() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/"+user_id+"/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	
	//Get Playlist Cover Image
	@Test (priority = 10)
	public void getPlaylistCoverImage() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlist_id+"/images");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	//Get Playlist Items
	@Test (priority = 11)
	public void getPlaylistItems() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	// Get Playlist
	@Test (priority = 12)
	public void getPlaylist() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlist_id+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	
	// Delete Remove Playlist Items
	@Test (priority = 13)
	public void removePlaylistItems() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{ \"tracks\": [{ \"uri\": \"spotify:track:5O932cZmzOZGOGZz9RHx20\" }]} ")
				//.queryParam("uris"," spotify:track:5O932cZmzOZGOGZz9RHx20")
				.when()
				.delete("https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	// Get Search for Item
	@Test (priority = 14)
	public void searchforItem() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParam("q","Arijit singh")
				.queryParam("type","track")
				.when()
				.get("https://api.spotify.com/v1/search");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	// Get Track Audio Analysis
	@Test (priority = 15)
	public void GetTrackAudioAnalysis() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-analysis/"+track_id+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	//Get Track Audio Features
	@Test (priority = 16)
	public void getTracksAudioFeatures() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-features");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	//Get Track Audio Features
	@Test (priority = 17)
	public void getTrackAudioFeatures() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-features/"+track_id+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	// Get Several Tracks
	@Test (priority = 18)
	public void getSeveralTracks() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.param("ids", track_id)
				.when()
				.get("https://api.spotify.com/v1/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	// Get Track
	@Test (priority = 19)
	public void get_Track() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/tracks/"+track_id+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
}
}
