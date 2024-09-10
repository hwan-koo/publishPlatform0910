
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import BookpublishBookPublishManager from "./components/listers/BookpublishBookPublishCards"
import BookpublishBookPublishDetail from "./components/listers/BookpublishBookPublishDetail"

import BookpurchaseBookPurchaseManager from "./components/listers/BookpurchaseBookPurchaseCards"
import BookpurchaseBookPurchaseDetail from "./components/listers/BookpurchaseBookPurchaseDetail"

import MemberMemberManager from "./components/listers/MemberMemberCards"
import MemberMemberDetail from "./components/listers/MemberMemberDetail"

import ReviewBookReviewManager from "./components/listers/ReviewBookReviewCards"
import ReviewBookReviewDetail from "./components/listers/ReviewBookReviewDetail"

import GenstoryGenStoryManager from "./components/listers/GenstoryGenStoryCards"
import GenstoryGenStoryDetail from "./components/listers/GenstoryGenStoryDetail"

import GenimageGenImageManager from "./components/listers/GenimageGenImageCards"
import GenimageGenImageDetail from "./components/listers/GenimageGenImageDetail"

import PayPaymenthistoryManager from "./components/listers/PayPaymenthistoryCards"
import PayPaymenthistoryDetail from "./components/listers/PayPaymenthistoryDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/bookpublishes/bookPublishes',
                name: 'BookpublishBookPublishManager',
                component: BookpublishBookPublishManager
            },
            {
                path: '/bookpublishes/bookPublishes/:id',
                name: 'BookpublishBookPublishDetail',
                component: BookpublishBookPublishDetail
            },

            {
                path: '/bookpurchases/bookPurchases',
                name: 'BookpurchaseBookPurchaseManager',
                component: BookpurchaseBookPurchaseManager
            },
            {
                path: '/bookpurchases/bookPurchases/:id',
                name: 'BookpurchaseBookPurchaseDetail',
                component: BookpurchaseBookPurchaseDetail
            },

            {
                path: '/members/members',
                name: 'MemberMemberManager',
                component: MemberMemberManager
            },
            {
                path: '/members/members/:id',
                name: 'MemberMemberDetail',
                component: MemberMemberDetail
            },

            {
                path: '/reviews/bookReviews',
                name: 'ReviewBookReviewManager',
                component: ReviewBookReviewManager
            },
            {
                path: '/reviews/bookReviews/:id',
                name: 'ReviewBookReviewDetail',
                component: ReviewBookReviewDetail
            },

            {
                path: '/genstories/genStories',
                name: 'GenstoryGenStoryManager',
                component: GenstoryGenStoryManager
            },
            {
                path: '/genstories/genStories/:id',
                name: 'GenstoryGenStoryDetail',
                component: GenstoryGenStoryDetail
            },

            {
                path: '/genimages/genImages',
                name: 'GenimageGenImageManager',
                component: GenimageGenImageManager
            },
            {
                path: '/genimages/genImages/:id',
                name: 'GenimageGenImageDetail',
                component: GenimageGenImageDetail
            },

            {
                path: '/pays/paymenthistories',
                name: 'PayPaymenthistoryManager',
                component: PayPaymenthistoryManager
            },
            {
                path: '/pays/paymenthistories/:id',
                name: 'PayPaymenthistoryDetail',
                component: PayPaymenthistoryDetail
            },



    ]
})
