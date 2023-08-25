import React, {useEffect, useState} from "react";
import ApiService from "./ApiService";
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
        await ApiService.editArticle(article)
            .then( (res) => {
                alert('글 수정 완료')
                navigate('/articles/'+id)
            });
    };

    const backToArticle = () => {
        navigate('/articles/'+id)
    }

    useEffect(() => {
        ApiService.fetchArticleById(id).then(response => {
            setArticle(response.data);
            console.log(article)
        })
    }, []);


    return(
        <>
            <Typography variant={"h3"} style={style}>글 수정하기</Typography>
            <FormControl id="article" fullWidth="True">

                <TextField label="글 제목" name="title" onChange={onChange} value={title}></TextField>

                <TextField
                    id="outlined-multiline-static"
                    label="글 내용"
                    name="content"
                    multiline
                    minRows={20}
                    value={content}
                    onChange={onChange}></TextField>
                <Button onClick={saveArticle}>수정</Button>
                <Button onClick={backToArticle}>취소</Button>
            </FormControl>
        </>
        )


}
const style = {
    display : 'flex',
    justifyContent: 'center'
}

export default EditArticle;