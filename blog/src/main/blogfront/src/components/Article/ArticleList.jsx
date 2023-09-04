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
                window.location.replace("/articles")
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
                            <a href={`/articles/${a.id}`} className="text-decoration-none" ><h4 className="fs-2 text-body-emphasis">{a.title}</h4></a>

                            {a.content.length > 5 ? <p class="text-secondary">{a.content.slice(0,5)} ... [더보기]</p> : <p class="text-secondary"> {a.content}</p>}
                        </ListItemText>
                            <button className="btn btn-success me-3">
                                <a href={`/editarticle/${a.id}`} className="text-decoration-none text-light" >수정</a>
                            </button>
                        <button className="btn btn-outline-success" onClick={() => deleteArticle(a.id)}>삭제</button>
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