
import './App.css';
import React from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.css';
import EmployeeList from './components/employeeList';



function App() {
 const handleCsvUpload=async(event)=>{
  event.preventDefault();
  const formData=new FormData();
  const files=event.target.files;
  if(files && files.length>0){
    const file=files[0]
    formData.append('file',file);
  try{
    const response=await axios.post('http://localhost:8080/upload-csv-file',formData,{
      headers:{
        'Content-Type': 'multipart/form-data'
      }
    });
    console.log(response.data);
  }catch(error){
    console.error('Error uploading CSV file:', error);
  }
}
 };
 const handleExcelUpload=async(event)=>{
  event.preventDefault()
  const formData=new FormData();
  const files=event.target.files;
  if(files && files.length>0){
    const file=files[0];
  formData.append('file',file);
  try{
    const response=await axios.post('http://localhost:8080/upload-excel-file',formData,{
      headers:{
        'Content-Type': 'multipart/form-data'
    }
    });
    console.log(response.data);
  } catch(error){
    console.error('Error uploading excel file:', error);
  }
}
};
const handleImageUpload=async(event)=>{
  event.preventDefault()
  const formData=new FormData();
  const files=event.target.files;
  if(files && files.length>0){
    const file = files[0];
    formData.append('file',file);
  try{
    const response=await axios.post('http://localhost:8080/upload-image-file',formData,{
      headers:{
        'Content-Type': 'multipart/form-data',
        'Access-Control-Allow-Origin': '*', 
    }
    });
    console.log(response.data);
  } catch(error){
    console.error('AxiosError', error);
  }
}
};

return (
  <div className="App">
    <div className="container mt-5">
      <div className="row mb-3">
        <div className="col">
          <form>
            <div className="form-group">
              <label htmlFor="csvFile">Select a CSV file</label>
              <input
                type="file"
                name="file"
                className="form-control-file"
                id="csvFile"
                accept=".csv"
                onChange={handleCsvUpload}
              />
            </div>
            <button type="submit" className="btn btn-primary">Submit CSV</button>
          </form>
        </div>
      </div>
      <div className="row mb-3">
        <div className="col">
          <form>
            <div className="form-group">
              <label htmlFor="excelFile">Select an Excel file</label>
              <input
                type="file"
                name="file"
                className="form-control-file"
                id="excelFile"
                accept=".xlsx"
                onChange={handleExcelUpload}
              />
            </div>
            <button type="submit" className="btn btn-primary">Submit Excel</button>
          </form>
        </div>
      </div>
      <div className="row mb-3">
        <div className="col">
          <form onSubmit={handleImageUpload} encType="multipart/form-data">
            <div className="form-group">
              <label htmlFor="imageFile">Select an image</label>
              <input
                type="file"
                name="file"
                className="form-control-file"
                id="imageFile"
                accept="image/*"
                onChange={handleImageUpload}
              />
            </div>
            <button type="submit" className="btn btn-primary">Submit Image</button>
          </form>
        </div>
      </div>
    </div>
    <div className="App">
      <div className="container mt-5"></div>
      <EmployeeList />
    </div>
  </div>
);

}


  
 
      
 

export default App;
