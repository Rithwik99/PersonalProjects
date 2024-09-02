import React, { useState, useEffect } from 'react';
import axios from 'axios';

function EmployeeList() {
  const [employeeList, setEmployeeList] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchEmployeeList = async () => {
      try {
        const response = await axios.get('http://localhost:8080/database');
        console.log('API Response:', response.data);  // Log the response data
        setEmployeeList(response.data);
      } catch (error) {
        console.error('Error fetching employee list:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchEmployeeList();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h1>Employee List</h1>
     
        <thead>
          <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone Number</th>
          </tr>
        </thead>
       
      <div className="mt-3">
        <a href="http://localhost:8080/export-to-pdf" className="btn btn-primary mr-2">Download PDF File</a>
        <a href="http://localhost:8080/export-to-excel" className="btn btn-primary">Download Excel File</a>
      </div>
    </div>
  );
}

export default EmployeeList;
