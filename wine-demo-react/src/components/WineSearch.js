import React, { Component } from 'react';
import './WineSearch.css'
import WineIcon from '../images/ICON.png';

import {Icon} from '@material-ui/core';
import { search } from '../configurations';
import { withRouter } from 'react-router-dom';



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
            searchInput: '',
            searchResult: [],
        })
    }

    onTextChange = (e) => {
        this.setState({
            searchInput: e.target.value
        })

        if (e.target.value == "") {
            this.setState({searchResult: []})
            return;
        }

        const url = search(e.target.value);
        fetch(url)
        .then( result => result.json())
        .then(data => this.setState({searchResult: data}))
        .catch(err => console.log(err));
    }

    startWithText = (text) => {
        return (text.startsWith(this.state.searchInput));
    }

    navigateToProductPage = (lotCode) => {
        let path = "/product/" + lotCode;
        this.props.history.push(path);
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
                        <Icon className="winesearch-textinput-closeicon" onClick={this.clearSearchInput}>close</Icon>
                    </div>
                    
                    <div className="winesearch-results">

                        {
                            this.state.searchResult.map((object, i) => {

                                var lotCodeSame = "";
                                var lotCodeDifferent = object.lotCode;
                                var descriptionSame = "";
                                var descriptionDifferent = object.description;

                                if (this.startWithText(lotCodeDifferent)){
                                    lotCodeSame = lotCodeDifferent.substring(0, this.state.searchInput.length)
                                    lotCodeDifferent = lotCodeDifferent.substring(this.state.searchInput.length, lotCodeDifferent.length)
                                }

                                if (this.startWithText(descriptionDifferent)){
                                    descriptionSame = descriptionDifferent.substring(0, this.state.searchInput.length)
                                    descriptionDifferent = descriptionDifferent.substring(this.state.searchInput.length, descriptionDifferent.length)
                                }


                                

                                return (
                                    <div key={i} className="winesearch-result" onClick={() => this.navigateToProductPage(object.lotCode)}>
                                        <div className="winesearch-result-row">
                                            <div className="winesearch-result-title">
                                                <div className="winesearch-result-title-same">{lotCodeSame}</div>
                                                <div className="winesearch-result-title-different">{lotCodeDifferent}</div>
                                            </div>
                                            <div className="winesearch-result-righttext">{object.volume + " L"}</div>
                                        </div>
                                        <div className="winesearch-result-row">
                                        <div className="winesearch-result-description">
                                                <div className="winesearch-result-description-same">{descriptionSame}</div>
                                                <div className="winesearch-result-description-different">{descriptionDifferent}</div>
                                            </div>
                                            <div className="winesearch-result-righttext">{object.tankCode}</div>
                                        </div>
                                    </div>
                                )
                            })
                            
                        }

                    </div>


                </div>

            </div>
        )
    }
}


export default withRouter(WineSearch);