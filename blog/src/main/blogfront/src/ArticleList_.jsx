import React, {useEffect, useState} from "react";
import {
    Button,
    ListItem,
    ListItemText,
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
    Link, ListItemIcon
} from "@material-ui/core";
import {useNavigate} from "react-router-dom";
import ApiService from "./ApiService";

const ArticleList_ = () => {
    const navigate = new useNavigate();

    const [articleList, setArticleList] = useState([]);

    const getarticleList = async () => {
        const resp = await (await ApiService.LoadArticles()).data;
        setArticleList(resp.data);

        const page = resp.pagination;
        console.log(page);
    }

    useEffect(() => {
        getarticleList();
    }, []);
    const editArticle = () => {
        navigate("/editArticle");
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
        // <Table>
        //     <TableHead>
        //         <TableRow>
        //             <TableCell>제목</TableCell>
        //         </TableRow>
        //     </TableHead>
        //     <TableBody>
        <div>
            {/*<ul>*/}
            {/*{articleList.map((article) => (*/}
            {/*    <li key={article.id}>*/}
            {/*        <Link to={`/articles/${article.id}`}>{article.title}</Link>*/}
            {/*    </li>*/}
            {/*))}*/}
            {/*</ul>*/}
        </div>
    );
};
export default ArticleList_;