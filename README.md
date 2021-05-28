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

## Explanatory Video of the Project
### <b>  Link for video demo in youtube: https://youtu.be/2oTMRRr7W6E </b> 

### Built With

This is the list of software and plugins that we are going to use in order to complete the project:
* [Oracle Java SE Development Kit 16](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html)
* [Lucene 8.8.2](https://lucene.apache.org/)
* [Maven](https://maven.apache.org/download.cgi)
* [Eclipse](https://www.eclipse.org/downloads) will be used to manage all the plug-ins and create the final executable

## About The Dataset
The dataset that was used was taken from <a href="https://www.kaggle.com/allen-institute-for-ai/CORD-19-research-challenge">Kaggle</a> and consists of documents related to COVID-19. These documents are mainly scientific articles and research focused. All of them are in .json format, and more specifically they have been separated into fields (inside the json file). In general, each document has fields for a unique "paper-id", a title, a list of authors and a body-text that consists of paragraphs. Other fields inside the json format contain information such as references, citations, acknowledgements etc. The whole collection the was kept for the purposes of the project consists of 550 documents.

<!-- USAGE EXAMPLES -->
## Usage
The usage of the search engine is easy, thanks to a simple graphic user interface, developed with Java Swing. The GUI consists of a search bar, where the user can write his queries, a search button and five field buttons to choose from, depending on where on the documents the user wants to search. The available choices are search by author, search by abstract, search by title, search by body-text and search in all of the above fields.  The results found by the search engine are returned in the main page of the application, with the words from the query highlighted inside. More information to this is explained below. 

## General
The purpose of the search engine that was implemented is the retrieval of COVID-19 related articles, by several search criteria that the user can define. The scientificity of the stored documents makes it easier for a user to safely and accurately search for possible correlations between COVID-19 and other diseases or even statistical data about the growth of the virus. Another use case implemented by the search is as simple as searching for specific authors based on their credibility in order to read their articles.


## Text Analysis and Indexing
  As mentioned above, the documents already come in a JSON format separated in fields. The JSON fields that were utilized for indexing are the metadata field, the abstract field and the body-text field. More specifically the metadata field was used in order to obtain information such as the title and the authors which were subfields. From the abstract field, the text field was only used. In the body-text field each paragraph/section of the article is a different subfield so in order to make one whole body-text string each individual subfield was parsed. The Java class responsible for parsing the JSON documents is “JsonParser.java”. This class takes as an input the directory in which the JSON documents are stored and for each one of them constructs a .txt file as an output. The txt file name is named after the paper id as is the JSON document file. Inside the txt we keep all the needed fields in pure text form. In the first line we keep the title, in the second line we keep the authors, in the third line we keep the abstract and in the fourth line we keep the body-text. Separating them in different lines helps us manipulate them easier when in the indexing phase. All the generated txt files are kept at a new directory called “txt_Documents” inside the Project folder. Fields such as the authors the abstract or the title, can sometimes be empty, in this case an empty line is kept in the txt file to signify that they don’t exist. 
  
  In terms of indexing, the class responsible for the process is “FileIndexer.java”. For the sake of our search engine a FSDirectory is created and used for indexing. When it comes to text analysis and the token stream build process the Lucene StandardAnalyzer was used. New documents are written in the index via the IndexWriter class which is provided. The document structure that is stored has four fields, title, authors, abstract and body each one taken from the corresponding line in the txt file. If the body part is empty, we do not store the document at all. If one of the other fields is empty then we don’t index the particular fields of the document. 


## Search
The search engine accepts both keyword and phrase searching. After the index is created, the responsible class for searching in our dataset is “FileSearcher.java”. This class uses an IndexReader and an IndexSearcher object (provided by the Lucene library) in order to read and search in the created index. The search engine can do a search in the document collection based on five criteria. More specifically it supports title search, author search, abstract search, body search and a search consisting of all of the fields. The results that are returned by the engine are limited to 100. After Lucene finds all the correlated documents, the “scoreDocs” method is used to sort the documents based on which is the most suitable for the submitted query. Inside these documents the “ResultsHighlighter.java” class conducts a process in which it highlights the best parts of the document and stores it as a new field inside the document. In this way we can highlight the important parts of the document at the GUI in the end. No embeddings were used for recommending better queries to the user, and for this reason no history of the queries is kept in our engine.

## Result Presentation
  The query's results are presented on a list format, in which the documents are listed based on their relevancy with the user's question. The user is presented with the top 10 results, based on their relevancy, and is able to see the next 10 results on the following page. Each individual result is a phrase of the document that includes the term from the user's query, with the term highlighted for better visibility as well as the id of the document.  In addition to this, the user is able to group up the results based on criteria, such as the value of a specific field , either title/ body/ author /abstract or all fields etc, but by default the results are presented based on the relevancy score of the document.
  
  First of, the program creates the window for the user to interact with. The necessary UI-related classes from the view package are called, along with the creation of the TopPanel, which houses the UI elements that were called, plus the field buttons that let the user choose to search in a specific field of the document. These buttons are: title, author, body, abstract and all.  After their creation, these buttons are attached to the TopPanel then "decorated" (font,color,size) with the setRadioButton() function.
  
  Then, the program checks if the query has a result or not. If it doesn't, it will inform the user with a message displayed on screen. If it does, the results from previous questions get deleted, then a new results panel gets created with proper formatting, like fonts and character size. After the panel creation we fetch the results from each document and call upon the lucene highlighter, by adding the "highlight" keyword on the document's field and calling the FileSearcher class, in order to highlight the search term in the fetched documents.Along with this, an "open" button is created, for the user to open the document  and read the aformentioned article. The fetched article , should the user want to read it, will be created in a different window through JFrame and have a JtextArea that the user can scroll through. 
  
  If the query has more than 10 results, we need more than one result page. Hence, we check if the results are more than 10 (by default there's always one page created, with more added depending on the amount of results), and create the pages necessary, given the number of results that the program returned. The relevancy of the shown results is decided through the scoreDocs variable that the FileSearcher class creates. Per Query, the results are printed based on that variable's value, fetched from the ScoreDoc[] array on the FileSearcher class.
  
  The user can then shuffle through the results with the buttons created at the bottom of the page.  The "previous" button will fetch the last 10 results (or nothing if the user is in the first page) and the "next" button the next 10 results. On top of that, per page of results created we also create a numbered list of buttons than the user can click, so e.g. if he wants to see the 55th result, he can click on the "6" button, which will bring on the foreground the 6th result page. This gives total control of the results to the user, so that he can he can find and read the specific article we wants to.
