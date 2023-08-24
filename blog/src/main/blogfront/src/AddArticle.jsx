import React, { Component } from "react";
import ApiService from "./ApiService";

class AddArticle extends Component {
    constructor(props) {
        super(props);

        this.state = {
            title : '',
            content : '',
            message: null
        }
    }
    onChange = (e) => {
        this.setState({
            [e.target.name] : e.target.value
        })
    }

    saveArticle = (e) => {
        e.preventDefault();

        let Article = {
            title: this.state.title,
            content: this.state.content
        }
        ApiService.addArticle(Article)
            .then( res => {
                this.setState({
                    message: '블로그 글 등록 완료'
                })
                console.log(this.state.message);
                this.props.history.push('/Articles');
            })
            .catch(err => {
                console.log('saveArticle() error', err);
            })
    }
    render() {
        return(
            <div>
                <h2>글 등록</h2>
                <form id="article">
                    <div>
                        <lable>글 제목 : </lable>
                        <input type="text" name="title" value={this.state.title} onChange={this.onChange} />
                    </div>
                    <div>
                        <textarea form="article" cols="50" rows="20" onChange={this.onChange}></textarea>
                        <input type="submit" value={"등록"} />
                    </div>

                </form>
            </div>
        )
    }
}

export default AddArticle;