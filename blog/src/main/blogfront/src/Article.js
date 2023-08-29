import {Divider, Typography} from "@material-ui/core";
import React from "react";
import {useNavigate} from "react-router-dom";
import ApiService from "./ApiService";

const Article = ({id, title, content}) => {
    const navigate = useNavigate();

    const editArticle = () => {
        navigate('/editArticle/'+id)
    }
    const deleteArticle = async () => {
        await ApiService.deleteArticle(id).then((res) =>{
            alert('삭제완료')
            navigate('/articles')
        });
    };
    const backToArticleList = () => {
        navigate('/articles')
    }

    return (
        <div>
            <div>
                <h2>{title}</h2>
                <br />
                <p>{content}</p>
            </div>
            <div>
                <button onClick={editArticle}>수정</button>
                <button onClick={deleteArticle}>삭제</button>
                <button onClick={backToArticleList}>목록</button>
            </div>
        </div>
    );
};

export default Article;