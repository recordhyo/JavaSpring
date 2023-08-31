import React, { useEffect, useState } from "react";
import ApiService from "./api/ApiService";
import {
    Button,
    ListItem,
    ListItemText,
    Typography,
    Table,
    List,
    TableCell,
    TableHead,
    TableRow,
    ListItemIcon
} from "@material-ui/core";
import {useNavigate, Link} from "react-router-dom";



function ArticleList() {

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

    const deleteArticle = (id) => {
        ApiService.deleteArticle(id)
            .then(function (response) {
                // handle success
                alert("삭제 완료")
                window.location.replace("/")
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
            <Typography style={style}>블로그 글 목록</Typography>
            <Button variant={"contained"} color={"primary"} onClick={addArticle}>글쓰기</Button>

                <List>
                    {articleList.map( (a)=> (
                    <ListItem key={a.id}>
                        <ListItemText>
                            <a href={`/articles/${a.id}`}>{a.title}</a>
                        </ListItemText>
                            <Button variant="contained">
                                <Link to={`/editArticle/${a.id}`} style={{ textDecoration: "none" }}>수정</Link>
                            </Button>
                        <Button variant="contained" onClick={() => deleteArticle(a.id)}>삭제</Button>
                    </ListItem>
                        ))}
                </List>

        </>
    );

}

const style = {
    display : 'flex',
    justifyContent: 'center'
}
export default ArticleList;