<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>


<!-- ABOUT THE PROJECT -->
## About The Project

This is a Java spring-boot-based microservice that is leveraging the [GNews](https://gnews.io/) API to search news articles with specific keyword or title. This Microservice also implements Redis Caching with 10 mins ttl which saves API calls to GNews server. Please update the apikey in the applicaiton.properties file. 

Endpoints:
* search with a keyword and limit::  localhost:8080/search?searchString=breaking&numberOfArticles=5  
* search with a specific title::   localhost:8080/search-title?title=Apple or Microsoft
* 

Of course, no one template will serve all projects since your needs may be different. So I'll be adding more in the near future. You may also suggest changes by forking this repo and creating a pull request or opening an issue. Thanks to all the people have contributed to expanding this template!

## Getting Started

You must use Java 17. You will also need to have Redis server running locally.


## Getting Started

1. Get a free API Key at from [GNews](https://gnews.io/)
2. Clone the repo
3. Install Redis in your local (e.g., Mac)
   ```sh
   $brew install redis
   redis server
   ```
4. To see your app interacting with redis-server on a seperate tab run the following
    ```sh
    $redis-cli MONITOR
    ``` 

