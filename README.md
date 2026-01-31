# Objective



Build a simple Android app using Kotlin and XML or Compose that demonstrates your technical skills, design decision-making, and familiarity with best practices in mobile app development.

Your task is to fetch data from the Apple RSS API and display the Top 100 Most Played Albums in Germany. The app should allow users to browse albums, view detailed album information, and save album information for offline access.



Please follow the UI of the provided mockup as closely as possible. (you will receive a Figma invite)



## Preparation Steps

Create a GitHub Repository:
Name the repository: challenge1.
Set the repository visibility to Public.
Commit Often:
Commit your work frequently to show the progress and the development process.
Each commit should include meaningful comments that describe the work done (e.g., "Added album list fetching logic", "Implemented search bar functionality").
Final Submission:
Once the app is complete, push all your changes to the challenge1 repository on GitHub.
Share the public repository link with us.
Keep the repository public until we confirm the review is complete.



## Requirements

### 1. Main Page: List of Albums

Fetch and display data from this API:
Apple RSS API - Top Albums  https://rss.marketingtools.apple.com
(sample feed URL https://rss.marketingtools.apple.com/api/v2/us/music/most-played/10/albums.json)
Display the following details for each album:
Name: The title of the album.
Artist Name: The artist’s name.
Artwork: Use the artworkUrl100 image.
Genres: A list of genre names associated with the album.
Implement the following features:
Search Bar:
Add a search bar to filter the list of albums.
The search should support a "contains" match for the album name and artist name.
Sections:
Display albums in two sections:
Featured: Albums fetched from the API.
Saved: Albums manually saved by the user.
Show the number of albums in each section next to the section name.
Allow users to switch between sections by tapping on the section name.
Save Albums:
Add an "Add" button to save an album to the "Saved" section.
Navigation to Detail Page:
When a user taps on an album's image, title, artist name, or arrow, navigate to the Detail Page.


### 2. Detail Page: Album Details

Display the following details for the selected album:
Artwork: Use the artworkUrl100 image.
Name: The album name.
Artist Name: The artist’s name.
Genres: A list of genre names.
View on Apple Music:
Include a button labeled "View on Apple Music".
When tapped, open the album's URL (url field) in the browser.


### 3. Persistence

Save the "Saved" albums locally.
Ensure saved albums are persisted and reloaded when the app is restarted.


### 4. Tests

Write basic unit tests for critical functionality (e.g., API fetching, persistence).