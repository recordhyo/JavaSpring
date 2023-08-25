import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import AddArticle from './AddArticle';
import EditArticle from "./EditArticle";
import ArticleList from "./ArticleList";
import ViewArticle from "./ViewArticle"
const AppRouter = () => {
    return(
        <div>
            <BrowserRouter>
                <div style={style}>
                    <Routes>
                        <Route exact path="/" element={<ArticleList/>}/>
                        <Route path="/articles" element={<ArticleList/>}/>
                        <Route path="/articles/:id" element={<ViewArticle/>}/>
                        <Route path="/addArticle" element={<AddArticle/>}/>
                        <Route path="/editArticle/:id" element={<EditArticle/>}/>
                    </Routes>

                </div>
            </BrowserRouter>
        </div>
    );
}
const style ={
    marginTop: '20px'
}

export default AppRouter;