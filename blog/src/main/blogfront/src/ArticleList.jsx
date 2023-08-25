import React, { useEffect, useState } from "react";
import ApiService from "./ApiService";
import {
    Button,
    ListItem,
    ListItemText,
    Typography,
    Link,
    Table,
    List,
    TableCell,
    TableHead,
    TableRow,
    ListItemIcon
} from "@material-ui/core";
import {useNavigate, useParams} from "react-router-dom";
import axios from "axios";



function ArticleList() {
    const {id} = useParams();
    const [articleList, setArticleList] = useState([]);
    const navigate = new useNavigate();


    useEffect(() => {
        ApiService.LoadArticles().then(response => {
            setArticleList(response.data);


        })
    }, []);
    const addArticle = () => {
        navigate("/addArticle");
    };

    const editArticle = () => {
        navigate("/articles"+id);
    };

    const deleteArticle = (id) => {
        ApiService.deleteArticle(id)
            .then(function (response) {
                // handle success
                alert("삭제 완료")
                navigate("/Articles")
                console.log(response);
            })
            .catch(function (error) {
                // handle error
                console.log(error);
            })
            .then(function () {
                // always executed
            });
    }


    return (
        <>
            <Typography variant={"h3"} style={style}>블로그 글 목록</Typography>
            <Button variant={"contained"} color={"primary"} onClick={addArticle}>글쓰기</Button>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>제목</TableCell>
                    </TableRow>
                </TableHead>
                <List>
                    {articleList.map( (a)=> (
                    <ListItem key={a.id}>
                        <a href={`/articles/${a.id}`}>{a.title}</a>
                        <Button onClick={editArticle}>수정</Button>
                        <Button>삭제</Button>
                    </ListItem>
                        ))}
                </List>

            </Table>
        </>

    );

}

const style = {
    display : 'flex',
    justifyContent: 'center'
}
export default ArticleList;