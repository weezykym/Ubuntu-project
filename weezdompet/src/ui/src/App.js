import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./pages/Home";
import Layout from "./pages/Layout";
import Customers from "./pages/Customers";
import Products from "./pages/Products";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path={'/'} element={<Layout />}>
            <Route index element={<Home />} />
            <Route path = {'customers'} element={<Customers />} />
            <Route path={'products'} element={<Products />}/>
            <Route path={'services'} element={<Services />} />
            <Route path={'vendors'} element={<Vendors />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
