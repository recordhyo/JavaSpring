import React, { Component } from "react";
import ApiService from "./ApiService";

class EditArticle extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id:'',
            title:'',
            content:'',
            message:null
        }
    }
    componentDidMount() {
        this.loadArticle();
    }
    loadArticle = () => {
        ApiService.fetchArticleById(window.localStorage.getItem("id"))
            .then( res => {
                let Article = res.data;
                this.setState({
                    id : Article.id,
                    title: Article.title,
                    content: Article.content
                })
            })
            .catch(err => {
                console.log('loadArticle() Error', err);
            });
    }
    onChange = (e) => {
        this.setState({
            [e.target.name] : e.target.value
        });
    }
    saveArticle = (e) => {
        e.preventDefault();

        let Article = {
            id: this.state.id,
            title: this.state.title,
            content: this.state.content

        }

        ApiService.editArticle(Article)
            .then( res => {
                this.setState({
                    message : "글 수정 완료"
                })
                this.props.history.push('/Articles');
            })
            .catch(err => {
                console.log('saveArticle() Error', err)
            })

    }

    render() {
        return(
            <div>
                <h2>글 수정 </h2>
                <form>
                    <div>
                        <lable>글 제목 : </lable>
                        <input type="text" name="title" value={this.state.title} onChange={this.onChange} />
                    </div>
                    <div>
                        <textarea cols="50" rows="50"></textarea>
                        <input type="submit" value={this.state.content} onChange={this.onChange} />
                    </div>

                </form>
            </div>
        )
    }

}

export default EditArticle;