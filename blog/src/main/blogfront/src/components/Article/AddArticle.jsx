import React, { useState } from "react";
import ApiService from "../../api/ApiService";
import {useNavigate} from "react-router-dom";
import {Button, FormControl, TextField, Typography} from "@material-ui/core";
import 'bootstrap/dist/css/bootstrap.css';

const AddArticle = () => {
    const navigate = new useNavigate();
    const [article, setArticle] = useState({
        title : '',
        content: '',
    });

    const {title, content } = article;
    const onChange = (e) => {
        const {value, name} = e.target;
        setArticle({
            ...article,
            [name]:value,
        });
    };

    const saveArticle = async () => {
        ApiService.addArticle(article)
            .then( (res) => {
                alert("글 등록 완료")
                navigate('/articles')
            })
            .catch(err => {
                console.log('saveArticle() error', err);
            })
    }

    const backToArticleList = () => {
        navigate('/articles')
    }

    return(
        <div style={style}>
            <h2 className="text-center">글쓰기</h2>
            <br/>
            <div className="text-end me-3">
            </div>
            <br/>
            <hr />
            <br/>
            <div className="mb-3">
                <label className="form-label">제목</label>

                <input type="email" className="form-control" name="title" onChange={onChange} />
            </div>
            <div className="mb-3">
                <label className="form-label">내용</label>
                <textarea className="form-control" name="content"
                          onChange={onChange} rows="15"></textarea>
            </div>
            <div className="text-center">
                <button className="btn btn-success me-3" onClick={saveArticle}>저장</button>
                <button className="btn btn-outline-success" onClick={backToArticleList}>취소</button>
            </div>
        </div>
        )

}
const style = {
    marginLeft: '50px',
    marginRight: '50px'
}

export default AddArticle;