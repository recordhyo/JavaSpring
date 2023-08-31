import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import AddArticle from './AddArticle';
import EditArticle from "./EditArticle";
import ArticleList from "./ArticleList";
import ViewArticle from "./ViewArticle"
import Login from "./Login";
import Header from "./components/Layout/Header/Header";
import Footer from "./components/Layout/Footer/Footer";
import Signup from "./Signup";
import Home from "./Home";
const AppRouter = () => {
    return(
        <div>

            <BrowserRouter>
                <div style={style}>
                    <Header />
                    <Routes>
                        <Route exact path="/" element={<Home/>}/>
                        <Route path="/articles" element={<ArticleList/>}/>
                        <Route path="/articles/:id" element={<ViewArticle/>}/>
                        <Route path="/addArticle" element={<AddArticle/>}/>
                        <Route path="/editArticle/:id" element={<EditArticle/>}/>
                        <Route path="/login" element={<Login/>}/>
                        <Route path="/signup" element={<Signup/>}/>
                    </Routes>
                    <Footer/>
                </div>
            </BrowserRouter>

        </div>
    );
}
const style ={
    marginTop: '20px'
}

export default AppRouter;