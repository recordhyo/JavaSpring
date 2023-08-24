import React, { Component } from "react";
import ApiService from "./ApiService";
import {Button, Table, TableBody, TableCell, TableHead, TableRow, Typography} from "@material-ui/core";
import AddArticle from "./AddArticle";


class BlogList extends Component {
    constructor(props) {
        super(props);

        this.state = {
            Articles: [],
            message: null
        }
    }
    componentDidMount() {
        this.reloadArticleList();
    }

    reloadArticleList = () => {
        ApiService.fetchArticles()
            .then(res => {
                this.setState({
                    Articles: res.data,
                })
            })
            .catch(err => {
                console.log(('reloadArticleList() Error!', err));
            })
    }

    deleteArticle = (id) => {
        ApiService.deleteArticle(id)
            .then( res => {
                this.setState({
                    message: '블로그 글 삭제 완료'
                });
                this.setState({
                    Articles : this.state.Articles.filter( Article => Article.id !== id)
                });
            })
            .catch(err => {
                console.log('deleteArticle() Error!', err);
            })
    }

    addArticle = () => {
        //window.localStorage.removeItem("id");
        this.props.history.push('/addArticle')
    }

    editArticle = (id) => {
        window.localStorage.setItem("id", id);
        this.props.history.push('/editArticle')
    }
    render() {
        return(
            <div>
                <Typography variant={"h2"} style={style}>블로그 글 목록</Typography>
                <Button variant={"contained"} color={"primary"} onClick={this.addArticle}>글 쓰기</Button>
                <Table>
                    <TableHead>
                    <TableRow>
                        <TableCell>제목</TableCell>
                        <TableCell>글 내용</TableCell>
                    </TableRow>
                    </TableHead>
                    <TableBody>
                    {this.state.Articles.map( Article =>
                        <TableRow key={Article.id}>
                            <TableCell>{Article.title}</TableCell>
                            <TableCell>{Article.content}</TableCell>
                            <TableCell>
                                <Button onClick= { () => this.editArticle(Article.id)}>수정</Button>
                            </TableCell>
                            <TableCell>
                                <Button onClick= { () => this.deleteArticle(Article.id)}>삭제</Button>
                            </TableCell>
                        </TableRow>

                    )}
                    </TableBody>
                </Table>
            </div>
        );
    }
}

const style = {
    display : 'flex',
    justifyContent: 'center'
}
export default BlogList;