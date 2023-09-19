import React, {useEffect} from "react";
import {useNavigate} from "react-router-dom";
import ApiService from "../../api/ApiService";

const Article = ({id, title, content, author, createddate, updateddate}) => {
    const navigate = useNavigate();

    useEffect(() => {

    }, []);
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

        <div style={style}>
            <h2 className="text-center">{title}</h2>
            <div className="text-end me-3"></div>
            <p className="text-center" color="gray">작성자 : {author}</p>
            <p className="text-center" color="gray">작성일 : {createddate} &nbsp;&nbsp; 수정일 : {updateddate}</p>
            <br/>
            <hr />
            <br/>
            <div className="text-center">
                <p>{content}</p>
            </div>
            <br/>
            <div className="text-center">
                <button className="btn btn-success me-3" onClick={editArticle}>수정</button>
                <button className="btn btn-outline-success me-3" onClick={deleteArticle}>삭제</button>
                <button className="btn btn-outline-secondary" onClick={backToArticleList}>목록</button>
            </div>
        </div>
    );
};

const style = {
    marginLeft: '50px',
    marginRight: '50px'
}

export default Article;