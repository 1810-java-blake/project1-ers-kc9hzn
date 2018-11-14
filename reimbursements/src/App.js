import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import './Include/bootstrap';
import {Header} from './Components/Header/Header.js';
import {Home} from './Components/Home/Home.js';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Header />
        
        <Home />
      </div>
    );
  }
}

export default App;
