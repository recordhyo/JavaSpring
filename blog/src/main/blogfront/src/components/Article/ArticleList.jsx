import React, { useEffect, useState } from "react";
import ApiService from "../../api/ApiService";
import {
    Button,
    ListItem,
    ListItemText,
    Typography,
    List
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
                window.location.replace("/api/articles")
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
        <div style={style}>
            <h2 className="text-center">전체글</h2>
            <br/>
            <div className="text-end me-3">
                <button className="btn btn-warning" onClick={addArticle}>글쓰기</button>
            </div>
            <br/>
            <hr />
            <br/>

                <List>
                    {articleList.map( (a)=> (
                    <ListItem key={a.id}>
                        <ListItemText>
                            <a href={`/articles/${a.id}`}><h4 className="fs-2 text-body-emphasis">{a.title}</h4></a>
                            <p>{a.content}</p>
                        </ListItemText>
                            <Button variant="contained">
                                <Link to={`/editArticle/${a.id}`} style={{ textDecoration: "none" }}>수정</Link>
                            </Button>
                        <Button variant="contained" onClick={() => deleteArticle(a.id)}>삭제</Button>
                    </ListItem>
                        ))}
                </List>

        </div>
    );

}

const style = {
    width : '80vw',
    margin: '0 auto'
    // marginLeft: '50px',
    // marginRight: '50px'
}
export default ArticleList;