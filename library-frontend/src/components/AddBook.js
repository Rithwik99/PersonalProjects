import React, { useState } from "react";
import axios from 'axios';

// Rename from addBook to AddBook (uppercase)
const AddBook = () => {
    const [book, setBook] = useState({
        isbn: '',
        title: '',
        author: '',
        publisher: '',
        yearOfPublication: '',
        edition: '',
        genre: '',
        shelfLocation: '',
        isAvailable: true
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setBook(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post('http://localhost:8080/save-books', book)
            .then(response => {
                console.log('Book added successfully', response.data);
                setBook({
                    isbn: '',
                    title: '',
                    author: '',
                    publisher: '',
                    yearOfPublication: '',
                    edition: '',
                    genre: '',
                    shelfLocation: '',
                    isAvailable: true
                });
            })
            .catch(error => {
                if (error.response) {
                    console.error('Response error:', error.response.data);
                    console.error('Response status:', error.response.status);
                    console.error('Response headers:', error.response.headers);
                } else if (error.request) {
                    console.error('Request error:', error.request);
                } else {
                    console.error('Error:', error.message);
                }
                console.error('Config:', error.config);
            });
    };

    return (
        <div>
            <h1>Add Book</h1>
            <form onSubmit={handleSubmit}>
                <div className="row mb-3">
                    <div className="col-3">
                        <input type="text" name="isbn" placeholder="ISBN" value={book.isbn} onChange={handleChange} className="form-control" required />
                    </div>
                    <div className="col-3">
                        <input type="text" name="title" placeholder="Title" value={book.title} onChange={handleChange} className="form-control" required />
                    </div>
                    <div className="col-3">
                        <input type="text" name="author" placeholder="Author" value={book.author} onChange={handleChange} className="form-control" required />
                    </div>
                </div>
                <div className="row mb-3">
                    <div className="col-3">
                        <input type="text" name="publisher" placeholder="Publisher" value={book.publisher} onChange={handleChange} className="form-control" required />
                    </div>
                    <div className="col-3">
                        <input type="number" name="yearOfPublication" placeholder="Year of Publication" value={book.yearOfPublication} onChange={handleChange} className="form-control" required />
                    </div>
                    <div className="col-3">
                        <input type="text" name="edition" placeholder="Edition" value={book.edition} onChange={handleChange} className="form-control" />
                    </div>
                </div>
                <div className="row mb-3">
                    <div className="col-3">
                        <input type="text" name="genre" placeholder="Genre" value={book.genre} onChange={handleChange} className="form-control" />
                    </div>
                    <div className="col-3">
                        <input type="text" name="shelfLocation" placeholder="Shelf Location" value={book.shelfLocation} onChange={handleChange} className="form-control" />
                    </div>
                    <div className="col-3">
                        <label className="form-check-label">
                            Available:
                            <input type="checkbox" name="isAvailable" checked={book.isAvailable} onChange={(e) => setBook(prevState => ({ ...prevState, isAvailable: e.target.checked }))} className="form-check-input" />
                        </label>
                    </div>
                </div>
                <button type="submit" className="btn btn-primary">Add Book</button>
            </form>
        </div>
    );
};

export default AddBook; // Export with uppercase name
