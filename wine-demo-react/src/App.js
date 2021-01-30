
import React, {Component} from 'react';
import ProductView from './components/ProductView';
import WineSearch from './components/WineSearch';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import './App.css'

export default function App() {
  return (
    <Router>
      <div className="background">

        <Switch>
   
          <Route path="/product">
            <ProductView />
          </Route>
          <Route path="/">
            <WineSearch />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}
