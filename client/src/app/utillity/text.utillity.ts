export default class TextUtillity {

    static shortenName(name: string): string {
        return name
            .split(' ')
            .slice(0, 2)
            .map(l => l[0].toUpperCase())
            .join('');
    }
}
