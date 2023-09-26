import React, { useEffect, useState } from "react";
import {useParams} from "react-router-dom";
import ApiService from "../../api/ApiService";
import Article from "./Article";

const ViewArticle = () => {
    const { id} = useParams();
    const [article, setArticle] = useState({});
    useEffect(() => {
        ApiService.fetchArticleById(id).then(response => {
            setArticle(response.data);
            console.log(response.data);
        })
    }, []);

    return(
         <div>
             <Article
                id={article.id}
                title={article.title}
                content={article.content}
                author={article.author}
                createddate={article.createddate}
                updateddate={article.updateddate}
                user={article.user}
             />
         </div>
    );
};
export default ViewArticle;

