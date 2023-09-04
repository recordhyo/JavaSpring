import React, {useEffect, useState} from "react";
import ApiService from "../../api/ApiService";
import {useNavigate, useParams} from "react-router-dom";
import {Button, FormControl, TextField, Typography} from "@material-ui/core";

const EditArticle = () => {
    const navigate = new useNavigate();
    const {id} = useParams();
    const [article, setArticle] = useState({
        id:0,
        title : '',
        content:'',
    });

    const {title, content } = article;

    const onChange = (e) => {
        const {value, name} = e.target;
        setArticle({
            ...article,
            [name]: value,

        });
    };

    const saveArticle = async () => {
        await ApiService.editArticle(id,article)
            .then( (res) => {
                alert('글 수정 완료')
                navigate('/articles/'+id)
            });
    };

    const backToArticle = () => {
        navigate('/articles/')
    }

    useEffect(() => {
        ApiService.fetchArticleById(id).then(response => {
            setArticle(response.data);
            console.log(article.id)
        })
    }, []);


    return(
        <div style={style}>
            <h2 className="text-center">글수정</h2>
            <br/>
            <div className="text-end me-3">
            </div>
            <br/>
            <hr />
            <br/>

            <form className="mb-3">
                <label className="form-label">제목</label>
                <input type="email" className="form-control" defaultValue={title} name="title" onChange={onChange} />
            </form>
            <form className="mb-3">
                <label className="form-label">내용</label>
                <textarea className="form-control" name="content"
                           defaultValue={content} onChange={onChange} rows="15"></textarea>
            </form>
            <div className="text-center">
                <button className="btn btn-success me-3" onClick={saveArticle}>수정</button>
                <button className="btn btn-outline-success" onClick={backToArticle}>취소</button>
            </div>
        </div>
        )


}
const style = {
    marginLeft: '50px',
    marginRight: '50px'
}

export default EditArticle;