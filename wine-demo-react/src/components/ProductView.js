import React, { Component } from 'react';
import { Icon } from '@material-ui/core';
import { withRouter } from 'react-router-dom';

import WIcon from '../images/Red.png';
import EditIcon from '../images/Edit.png';

import { getDetails, getYearBreakdown, getVarietyBreakdown, getRegionBreakdown, getYearVarietyBreakdown } from '../configurations';

import './ProductView.css'

class ProductView extends Component {

    buttons = ["Year", "Variety", "Region", "Year & Variety"]

    constructor(props) {
        super(props);
        this.state = {
            details: {},
            tableInfo: {},
            tableType: "",
            showMore: 1,
            editMode: false,
        }
    }

    onEditButtonPress = () => {
        this.setState({editMode: !this.state.editMode})
    }

    componentDidMount() {
        fetch(getDetails(this.props.match.params.lotcode))
            .then(result => result.json())
            .then(data => this.setState({ details: data }))

        this.yearBreakdown();

    }

    buttonPress = (type) => {
        this.setState({showMore: 1});

        if (type === "Year") {
            this.yearBreakdown()
        } else if (type == "Variety") {
            this.varietyBreakdown()
        } else if (type == "Region") {
            this.regionBreakdown()
        } else if (type == "Year & Variety") {
            this.yearVarietyBreakdown()
        }


    }

    yearBreakdown() {
        fetch(getYearBreakdown(this.props.match.params.lotcode))
            .then(result => result.json())
            .then(data => this.setState({ tableInfo: data, tableType: "Year" }))
    }

    varietyBreakdown() {
        fetch(getVarietyBreakdown(this.props.match.params.lotcode))
            .then(result => result.json())
            .then(data => this.setState({ tableInfo: data, tableType: "Variety" }))
    }

    regionBreakdown() {
        fetch(getRegionBreakdown(this.props.match.params.lotcode))
            .then(result => result.json())
            .then(data => this.setState({ tableInfo: data, tableType: "Region" }))
    }

    yearVarietyBreakdown() {
        fetch(getYearVarietyBreakdown(this.props.match.params.lotcode))
            .then(result => result.json())
            .then(data => this.setState({ tableInfo: data, tableType: "Year & Variety" }))
    }


    table = (type, data) => {
        if (data == null) return;


        return (

            <div className="productview-table-container">
                <div className="productview-table-row-header">
                    <div className="productview-table-left-text">{type}</div>
                    <div className="productview-table-right-text">Percentage</div>
                </div>

                {
                    data.map((object, i) => {
                        if (i > this.state.showMore*5) {
                            return;
                        }
                        if (i == this.state.showMore*5) {
                            return (
                                <div className="productview-table-row">
                                    <div className="productview-showmore" onClick={() => this.setState({showMore: this.state.showMore+1})}>
                                        Show More
                                        <Icon className="productview-showmore-icon ">expand_more</Icon>
                                    </div>
                                </div>
                            )
                        }
                        return (
                            <div key={i} className="productview-table-row">
                                <div className="productview-table-left-text">{object.key}</div>
                                <div className="productview-table-right-text">{object.percentage}</div>

                            </div>
                        )

                    })
                }
            </div>

        )

    }


    render() {
        return (

            <div className="productview-container">
               
                <img className={this.state.editMode? "productview-editbutton-editing":"productview-editbutton"} src={EditIcon} alt="" onClick={this.onEditButtonPress}/>

                <div className="productview-backbutton" onClick={() => this.props.history.goBack()}>
                    <Icon>arrow_back</Icon>
                </div>

                <h1 className="productview-lotcode">
                    <img className="productview-wicon" src={WIcon} alt="" />
                    {this.props.match.params.lotcode}
                </h1>
                <div className="productview-description">{this.state.details.description}</div>


                <h5 className="productview-item-row">
                    <div className="productview-item-title">Volume</div>
                    <div className="productview-item-value">{this.state.details.volume}</div>
                </h5>

                <h5 className="productview-item-row">
                    <div className="productview-item-title">Tank code</div>
                    <div className="productview-item-value">{this.state.details.tankCode}</div>
                </h5>

                <h5 className="productview-item-row">
                    <div className="productview-item-title">Product state</div>
                    <div className="productview-item-value">{this.state.details.productState}</div>
                </h5>

                <h5 className="productview-item-row">
                    <div className="productview-item-title">Owner</div>
                    <div className="productview-item-value">{this.state.details.ownerName}</div>
                </h5>

                <div className="productview-buttonrow">
                    {
                        this.buttons.map((object, i) => {

                            return (
                                <div key={i} className={this.state.tableType === object ? "productview-button-selected" : "productview-button"} 
                                    onClick={() => {
                                        this.setState({ tableType: object });
                                        this.buttonPress(object)
                                    }}>
                                    {object}
                                </div>
                            )

                        })
                    }

                </div>

                {
                    this.table(this.state.tableType, this.state.tableInfo.breakdown)
                }

            </div>


        )
    }

}

export default withRouter(ProductView);