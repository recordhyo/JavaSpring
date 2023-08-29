import React, { useState } from "react";
import ApiService from "./ApiService";
import {useNavigate} from "react-router-dom";
import {Button, FormControl, TextField, Typography} from "@material-ui/core";

const AddArticle = () => {
    const navigate = new useNavigate();
    const [article, setArticle] = useState({
        title : '',
        content:'',
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
        <>
            <Typography variant={"h3"} style={style}>글 등록하기</Typography>
            <FormControl id="article" fullWidth={true}>

                <TextField label="글 제목" name="title" onChange={onChange} value={title}></TextField>

                <TextField
                    id="outlined-multiline-static"
                    label="글 내용"
                    name="content"
                    multiline
                    minRows={20}
                    value={content}
                    onChange={onChange}></TextField>
                <Button onClick={saveArticle}>저장</Button>
                <Button onClick={backToArticleList}>취소</Button>
            </FormControl>
        </>
        )

}
const style = {
    display : 'flex',
    justifyContent: 'center'
}

export default AddArticle;