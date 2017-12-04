export default class TextUtillity {

    static shortenName(name: string): string {
        return name
            .split(' ')
            .slice(0, 2)
            .map(l => l[0].toUpperCase())
            .join('');
    }

    static randomId() {
        const options = "abcdefghijklmnopqrstuvwxyz123456789";

        let result = "";

        for (let i = 0; i < 8; i++) {
            result += options[Math.floor(Math.random() * options.length)];
        }

        return result;
    }
}
