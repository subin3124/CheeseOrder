import logo from './logo.svg';
import './App.css';
import DragFromOutsideLayout from "./DragFromOutsideLayout";
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import QrCode from "./QrCode";
function App() {
  return (
      <BrowserRouter>
          <Routes>
        <Route path="/" element={<DragFromOutsideLayout/>}/>
        <Route path="/QrCode/:tableId" element={<QrCode/>}/>
          </Routes>
      </BrowserRouter>
      );
}

export default App;
