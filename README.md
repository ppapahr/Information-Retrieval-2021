<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Thanks again! Now go create something AMAZING! :D
***
***
***
*** To avoid retyping too much info. Do a search and replace for the following:
*** github_username, repo_name, twitter_handle, email, project_title, project_description
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->


<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/github_username/repo_name">
    <img src="images/logo2.png" alt="Logo" width="100" height="100">
  </a>

  <h3 align="center">Information Retrieval 2021</h3>

  <p align="center">
    Search engine on COVID-19 related documents
    <br />
    <a href="https://github.com/ppapahr/Information-Retrieval-2021/tree/main/Documents"><strong>Explore a sample of the documents »</strong></a>
    <br />
    <br />
    <h4 align="center">Team:
    <br />
    Panagiotis Giannakopoulos
    <br />
    Filippos-Apostolos Papachristou </h4>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#about-the-dataset">About The Dataset</a>
    </li>
    <li><a href="#usage">Usage</a>
      <ul>
        <li><a href="#general">General</a></li>
        <li><a href="#text-analysis-and-indexing">Text Analysis and Indexing</a></li>
        <li><a href="#search">Search</a></li>
        <li><a href="#result-presentation">Result Presentation</a></li>
      </ul>
      </li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project
This project was developed for the course of "Information Retrieval" in Department of Computer Science and Engineering in the University of Ioannina under the supervision
of Professor Evaggelia Pitoura. The project is about the development of a search engine specifically for COVID-19 related documents, using the open-source library Lucene in java.

### Built With

This is the list of software and plugins that we are going to use in order to complete the project:
* [Oracle JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Lucene 8.8.2](https://lucene.apache.org/)
* [Maven](https://maven.apache.org/download.cgi)

## About The Dataset
The dataset that was used was taken from <a href="https://www.kaggle.com/allen-institute-for-ai/CORD-19-research-challenge">Kaggle</a> and consists of documents related to COVID-19. These documents are mainly scientific articles and research focused. All of them are in .json format, and more specifically they have been separated into fields (inside the json file). In general, each document has fields for a unique "paper-id", a title, a list of authors and a body-text that consists of paragraphs. Other fields inside the json format contain information such as references, citations, acknowledgements etc.   

<!-- USAGE EXAMPLES -->
## Usage
The usage of the program will be fairly easy through a simple user interface, where queries can be submitted through a field and results, in general, will be accessible through a list with the relevant document ids as well as other highlighted information as explained furthermore below. 

## General
The purpose of the search engine that is going to be implemented is the retrieval of COVID-19 related articles, by several search criteria that the user can define. The scientificity of the stored documents will make it easier for a user to safely and accurately search for possible correlations between COVID and other diseases or even statistical data about the growth of the virus. Another use case of the search could be as simple as searching for specific authors based on their credibility in order to read their articles.


## Text Analysis and Indexing
As mentioned above, the documents already come in a json format separated in fields. The basic fields that will be utilized in indexing are, the authors of the article, the full body text, the title and maybe the abstract of the paper. One reversed index could be created for the authors of each document in case a user wants to search quickly for a document based on who wrote it. The full body text of each document, when indexed, could offer a more analytical search but on the other hand will cause very large indexes hence increasing search time by a large margin. The abstract part of the documents could be a smarter and faster way of searching about the contents of the dataset as it provides a small summary of the body text. Finally, an index on the titles could also provide the user with the capability of searching for a paper id by only knowing the title of the document.  All indexes will be created with the IndexWriter method in Lucene and follow the class SimpleFSFirectory which is the simplest offered directory type in the library as the other types are used for more advanced system use cases such as multithreading etc.

## Search
The search engine is based on keyword searching, where the user submits a query or a word and the program finds relevant results on the database. Once the IndexWriter is done creating the index, the IndexSearcher can be used combined with a Query (submitted by the user) to search for the desired results. This part will utilize embeddings in order to make the search more efficient. The program will also able to search on a specific field of a document, whether it is the title of the document or the body, by changing the corresponding argument of the .searchIndex() command. One of the program's function is keeping the user's query history. This is useful to the user since it will show relevant keywords that other users have searched for when requesting similar results. 

## Result Presentation
The query's results will be presented on a list format, in which the documents will be listed based on their relevancy with the user's question. The user will be presented with the top 10 
results, based on their relevancy, and will be able to see the next 10 results on the following page. Each individual result will be a phrase of the document that includes the term
from the user's query, with the term highlighted for better visibility as well as the id of the document.  In addition to this, the user will be able to group up the results based on several criteria, 
such as the document's date of creation, the value of a specific field (title/body/author), the relevancy score of the document etc. 










