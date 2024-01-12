import React from "react";
import NewFarmerForm from "@/components/NewFarmerForm/indexOne";
import Footer from "@/components/Home/Footer";
import Header from "@/components/Home/Header";
import styles from "@/app/novoAssociado/index.module.scss"

export default function NewRegisterFarmer() {
    return (
        <div>
            <div>
            <Header />
            </div>
            <div>
                <h1 className={styles.title}>
                    Atividade Rural
                </h1>
            </div>
            <div>
                <NewFarmerForm />
            </div>
            <div>
            <Footer />
            </div>
        </div>
    );
}