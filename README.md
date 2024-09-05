This Spring Boot application includes features for image compression, file upload, deletion, and metadata retrieval.
Features

    Image Compression: Compress images before saving them.
    File Upload: Upload files with automatic image compression.
    File Deletion: Delete uploaded files.
    File Metadata: Retrieve metadata for all stored files, including file name, URL, file size, and upload date.
    Summary: Get a summary of the total number of stored files and total storage occupied.
    Security: Basic authentication is used to secure endpoints.

Setup
Prerequisites

    Java 17 or later
    Gradle 8.x or later

Build the Project

bash

./gradlew build

Run the Application

bash

    ./gradlew bootRun

Configuration

Edit src/main/resources/application.properties to configure application settings:

properties

# Path to save the uploaded files
file.upload-dir=/path/to/uploads/

# Enable Caching
spring.cache.type=simple

# Security settings
spring.security.user.name=admin
spring.security.user.password=admin123

    file.upload-dir: Specify the directory where uploaded files will be saved.
    spring.cache.type: Configure the cache type (default is simple).
    spring.security.user.name and spring.security.user.password: Set the username and password for basic authentication.

Endpoints
Upload File

    URL: /files/upload
    Method: POST
    Parameters: file (multipart file)
    Description: Uploads and compresses an image file.
    Response: { "fileName": "<file-name>", "url": "/files/<file-name>" }

Delete File

    URL: /files/delete/{fileName}
    Method: DELETE
    Path Variable: fileName (name of the file to delete)
    Description: Deletes the specified file.
    Response: { "status": "deleted" } or { "status": "failed" }

List Files Metadata

    URL: /files/list
    Method: GET
    Description: Retrieves metadata for all stored files.
    Response: { "files": [ { "fileName": "<name>", "url": "<url>", "fileSize": <size>, "uploadDate": "<date>" } ] }

Summary

    URL: /files/summary
    Method: GET
    Description: Provides a summary of the total number of files and total storage occupied.
    Response: { "totalFiles": <count>, "totalSize": <size> }

Security

The application uses basic authentication for the following endpoints:

    /files/upload
    /files/delete/**

The default credentials are:

    Username: admin
    Password: admin123

Caching

Caching is enabled to improve performance for file listing and metadata queries. The default caching configuration is used.
Notes

    Ensure that the file.upload-dir directory exists or is created by the application.
    Adjust security and caching settings based on your specific requirements.