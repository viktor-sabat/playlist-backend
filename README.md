# Playlist Backend

## Description

PlaylistHub is a backend service for managing songs and playlists, built using Java Spring Boot. It uses an H2 database for persistence and includes a set of API endpoints to handle operations related to songs, playlists, and users. You can use Postman to run API tests using the provided JSON collection.

## Technologies Used

- **Java Spring Boot**: For the backend service and API development.
- **H2 Database**: In-memory database for development and testing.
- **Postman**: For API testing and validation.

## Getting Started

1. **Clone the Repository:**

    ```sh
    git clone https://github.com/viktor-sabat/playlist-backend
    ```

2. **Navigate to the Project Directory:**

    ```sh
    cd playlist-backend
    ```

3. **Build and Run the Application:**

    ```sh
    ./mvnw spring-boot:run
    ```

4. **Access the H2 Console:**

    - The H2 console can be accessed at `http://localhost:8080/h2/`.
    - Ensure you have the following settings to connect:
        - **User Name**: `user`
        - **Password**: `password`

5. **Database Schema Queries:**

    To view the data in each table, use the following SQL SELECT statements:

    ```sql
    -- Select all users
    select * from app_user;

    -- Select all playlists
    select * from playlist;

    -- Select all songs
    select * from song;

    -- Select playlist-song pairs from the join table
    select * from playlist_song;
    ```

## API Endpoints

Below are the main API endpoints for managing songs, playlists, and users. The request type badge and description are on one line, with the code block containing the endpoint URL on the next line.

![POST](https://img.shields.io/badge/POST-blue)    Create a new Song  

    /api/v1/song
    
![PUT](https://img.shields.io/badge/PUT-yellow)    Update an existing Song  

    /api/v1/song/{id}
 

![GET](https://img.shields.io/badge/GET-green)    Retrieve all Songs from a specific album  
   
    /api/v1/song?album=…
 

![GET](https://img.shields.io/badge/GET-green)    Retrieve all Songs with a length between `minLength` and `maxLength`  
   
    /api/v1/song?minLength=…&maxLength=…
    

![POST](https://img.shields.io/badge/POST-blue)    Create a new User  

    /api/v1/user
    

![POST](https://img.shields.io/badge/POST-blue)    Create a new Playlist for a specific User  
    
    /api/v1/user/{id}/playlist
  

![POST](https://img.shields.io/badge/POST-blue)    Add an existing Song to a Playlist  
    
    /api/v1/user/{user-id}/playlist/{playlist-id}/song
   

![DELETE](https://img.shields.io/badge/DELETE-red)    Remove a Song from a Playlist  
   
    /api/v1/user/{user-id}/playlist/{playlist-id}/song/{song-id}
 

![GET](https://img.shields.io/badge/GET-green)    Retrieve all details for a specific User, including their Playlists and Songs  
    
    /api/v1/user/{id}
   

![DELETE](https://img.shields.io/badge/DELETE-red)    Delete a User and all their associated Playlists  
    
    /api/v1/user/{id}


## How to Run Tests with Postman

1. **Install Postman**: Download and install Postman from [Postman’s website](https://www.postman.com/downloads/).

2. **Import the Postman Collection**:

    - Open Postman.
    - Click on the "Import" button in the top left corner.
    - Select the "File" tab and choose `playlist-backend\src\test\postman_tests\playlists_backend.postman_collection.json`.
    - Click "Open" to import the collection.

3. **Run the Tests**:

    - Navigate to the "Collections" tab in Postman.
    - Select the imported collection.
    - Choose the requests you want to send from the collection.
    - Click `Send` to execute the selected requests.
