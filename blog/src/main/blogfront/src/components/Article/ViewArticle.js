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

        })
    }, []);

    return(
         <div>
             <Article
                id={article.id}
                title={article.title}
                content={article.content}/>

         </div>
    );
};
export default ViewArticle;

