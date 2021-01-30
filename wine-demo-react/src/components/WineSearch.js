import React, { Component } from 'react';
import './WineSearch.css'
import WineIcon from '../images/ICON.png';

import Icon from '@material-ui/core/Icon';



class WineSearch extends Component {

    constructor(props) {
        super(props);
        this.state = {
            searchInput: '',
            searchResult: []
        }
    }

    clearSearchInput = () => {
        this.setState({
            searchInput: ''
        })
    }

    onTextChange = () => {

    }

    


    render() {
        return (
            <div className="winesearch-container"> 
                <div className="winesearch-header">
                    <h3 className="winesearch-header-text">Wine search</h3>
                    <img className="winesearch-header-icon" src={WineIcon} alt=""/>
                </div>
                <div className="winesearch">

                    <div className="winesearch-searchbar">
                        <Icon className="winesearch-textinput-searchicon">search</Icon>
                        <input className="winesearch-textinput" value={this.state.searchInput} onChange={this.onTextChange}/>
                        <Icon className="winesearch-textinput-closeicon">close</Icon>
                    </div>
                    

                </div>

            </div>
        )
    }
}


export default WineSearch;