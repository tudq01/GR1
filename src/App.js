import React, { Suspense } from "react";
import "./App.css";
import { Route, Routes, BrowserRouter } from "react-router-dom";
import Info from "./pages/ex1/Info";
import Home from "./pages/home/Home";
import ImageGrid from "./pages/ex2/ImageGrid";
import Game from "./pages/ex4/Game";
import Map from "./pages/ex3/Map";
function App() {
  return (
    <BrowserRouter>
      <Suspense fallback={<div>Loading ...</div>}>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/ex1" element={<Info />} />
          <Route path="/ex2" element={<ImageGrid />} />
          <Route path="/ex3" element={<Map />} />
          <Route path="/ex4" element={<Game />} />
        </Routes>
      </Suspense>
    </BrowserRouter>
  );
}

export default App;
