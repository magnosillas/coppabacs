import React from "react";
import NewSeedForm from "@/components/NewSeedForm/Index";
import Footer from "@/components/Home/Footer";
import Header from "@/components/Home/Header";
import styles from "@/app/associados/novoAssociado/index.module.scss"

export default function NewRegisterFarmer() {
    return (
        <div>
            <div>
            <Header />
            </div>
            <div>
                <h1 className={styles.title}>
                    Cadastro de Cultivares Crioulas
                </h1>
            </div>
            <div>
                <NewSeedForm />
            </div>
            <div>
            <Footer />
            </div>
        </div>
    );
}