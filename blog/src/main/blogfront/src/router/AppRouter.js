import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import PrivateRouter from "./PrivateRouter";
import AddArticle from "../components/Article/AddArticle";
import EditArticle from "../components/Article/EditArticle";
import ArticleList from "../components/Article/ArticleList";
import ViewArticle from "../components/Article/ViewArticle"
import Login from "../components/User/Login";
import Signup from "../components/User/Signup";
import Header from "../components/Layout/Header/Header";
import Footer from "../components/Layout/Footer/Footer";
import Home from "../Home";
import Mypage from "../components/User/Mypage";
import Token from "../Token";
import Oauth2Success from "../components/User/Oauth2Success";
function AppRouter(){
    return(


            <BrowserRouter>
                <Header />
                <div style={style}>
                    <Routes>
                        <Route exact path="/" element={<Home/>}/>
                        <Route path="/articles" element={<ArticleList/>}/>
                        <Route element={<PrivateRouter/>}>
                            <Route path="/articles/:id" element={<ViewArticle/>}/>
                            <Route path="/addArticle" element={<AddArticle/>}/>
                            <Route path="/editArticle/:id" element={<EditArticle/>}/>
                            <Route path="/mypage" element={<Mypage/>}/>
                        </Route>
                        <Route path="/login/oauth2/code/kakao" element={<Token/>}/>
                        <Route path="/success/:userid" element={<Oauth2Success/>}/>
                        <Route path="/userlogin" element={<Login/>}/>
                        <Route path="/usersignup" element={<Signup/>}/>
                    </Routes>
                </div>
                <Footer/>
            </BrowserRouter>

    );
}
const style ={
    padding: '30px',
    width: '100%',
    minHeight:'100vh'

}

export default AppRouter;