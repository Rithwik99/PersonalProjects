import axios from 'axios';
import React,{useEffect, useState}from 'react';

const BookList =()=>{
    const [books,setBooks]=useState([]);
    useEffect(()=>{
        axios.get('http://localhost:8080/get-books')
        .then(Response=>{
            setBooks(Response.data);
        })
        .catch(error =>{
            console.error('there was an error fecthing  the book!',error);
        });
    },[]);

    return(
        <div>
            <h1>List of books</h1>
            <ul>
                {books.map(book=>(
                    <li key ={book.id}>
                    {book.title}by{book.author}
                    </li>
                ))}
            </ul>
        </div>
    );
};
export default BookList;